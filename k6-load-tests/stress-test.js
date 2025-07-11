import { simulateUserJourney, performLogin, getAppointments, getPatients, getDoctors } from './common.js';
import { sleep } from 'k6';

/**
 * PRUEBAS DE ESTRÉS
 * 
 * Este script incrementa gradualmente la carga para encontrar el punto de fallo
 * de la aplicación dental y determinar su capacidad máxima.
 * 
 * Escenarios:
 * - Ramp-up inicial: 0 -> 100 usuarios en 2 minutos
 * - Incremento gradual: 100 -> 500 usuarios en 15 minutos
 * - Carga máxima sostenida: 500 usuarios por 5 minutos
 * - Ramp-down: 500 -> 0 usuarios en 3 minutos
 */

export const options = {
    stages: [
        { duration: '2m', target: 100 },  // 0 -> 100 usuarios en 2 minutos
        
        //{ duration: '3m', target: 200 },  // 100 -> 200 usuarios en 3 minutos
        //{ duration: '3m', target: 300 },  // 200 -> 300 usuarios en 3 minutos
        //{ duration: '3m', target: 400 },  // 300 -> 400 usuarios en 3 minutos
        //{ duration: '3m', target: 500 },  // 400 -> 500 usuarios en 3 minutos
        //{ duration: '3m', target: 600 },  // 500 -> 600 usuarios en 3 minutos
        
        //{ duration: '5m', target: 600 },  // Mantener 600 usuarios por 5 minutos
        
        //{ duration: '3m', target: 0 },    // 600 -> 0 usuarios en 3 minutos
    ],

    thresholds: {
        http_req_duration: [
            'p(50)<2000',   // 50% de requests < 2 segundos
            'p(90)<5000',   // 90% de requests < 5 segundos
            'p(95)<8000',   // 95% de requests < 8 segundos
            'p(99)<15000',  // 99% de requests < 15 segundos
        ],
        
        http_req_failed: ['rate<0.15'],   // < 15% de errores
        
        http_reqs: ['rate>5'],            // Mínimo 5 requests/segundo
        
        errors: ['rate<0.15'],            // < 15% de errores personalizados
    },

    vus: 0,  // Se controla por las etapas
    
    ext: {
        loadimpact: {
            distribution: {
                'amazon:us:ashburn': { loadZone: 'amazon:us:ashburn', percent: 100 },
            },
        },
    },
};

/**
 * Función principal que ejecuta cada usuario virtual
 * Versión simplificada para pruebas de estrés
 */
export default function () {
    // Para pruebas de estrés, usamos un flujo más simple
    // para maximizar la carga en el sistema
    
    // 1. Login rápido
    performLogin();
    sleep(0.5);
    
    // 2. Operaciones críticas en paralelo
    const promises = [
        getAppointments(),
        getPatients(),
        getDoctors()
    ];
    
    // 3. Pausa mínima
    sleep(0.5);
}

/**
 * Función alternativa para estrés máximo
 * Descomenta esta función y comenta la anterior para estrés extremo
 */
/*
export default function () {
    // Estrés máximo - solo operaciones críticas
    getAppointments();
    sleep(0.1);
    getPatients();
    sleep(0.1);
    getDoctors();
    sleep(0.1);
}
*/

/**
 * Configuración de setup
 */
export function setup() {
    console.log('🔥 Iniciando pruebas de estrés...');
    console.log('📊 Configuración:');
    console.log('   - Ramp-up inicial: 0 -> 100 usuarios (2 min)');
    console.log('   - Incremento gradual: 100 -> 600 usuarios (15 min)');
    console.log('   - Carga máxima: 600 usuarios (5 min)');
    console.log('   - Ramp-down: 600 -> 0 usuarios (3 min)');
    console.log('   - Duración total: 25 minutos');
    console.log('🎯 Objetivos:');
    console.log('   - Encontrar punto de fallo');
    console.log('   - Determinar capacidad máxima');
    console.log('   - Identificar cuellos de botella');
    console.log('   - < 15% de errores durante estrés');
}

/**
 * Configuración de teardown
 */
export function teardown(data) {
    console.log('✅ Pruebas de estrés completadas');
    
    if (data && data.metrics) {
        console.log('📈 Métricas finales:');
        const metrics = data.metrics;
        
        if (metrics.http_reqs && metrics.http_reqs.values) {
            console.log(`   - Requests totales: ${metrics.http_reqs.values.count || 'N/A'}`);
            if (metrics.http_reqs.values.rate) {
                console.log(`   - Throughput: ${metrics.http_reqs.values.rate.toFixed(2)} req/s`);
            }
        }
        
        if (metrics.http_req_duration && metrics.http_req_duration.values) {
            console.log(`   - Tiempo promedio: ${metrics.http_req_duration.values.avg || 'N/A'}ms`);
            if (metrics.http_req_duration.values['p(95)']) {
                console.log(`   - Tiempo p95: ${metrics.http_req_duration.values['p(95)']}ms`);
            }
            if (metrics.http_req_duration.values['p(99)']) {
                console.log(`   - Tiempo p99: ${metrics.http_req_duration.values['p(99)']}ms`);
            }
        }
        
        if (metrics.http_req_failed && metrics.http_req_failed.values) {
            const errorRate = (metrics.http_req_failed.values.rate * 100).toFixed(2);
            console.log(`   - Tasa de errores: ${errorRate}%`);
        }
        
        // Análisis de resultados
        console.log('🔍 Análisis:');
        const errorRate = metrics.http_req_failed?.values?.rate || 0;
        const avgResponseTime = metrics.http_req_duration?.values?.avg || 0;
        const p95ResponseTime = metrics.http_req_duration?.values?.['p(95)'] || 0;
        
        if (errorRate > 0.1) {
            console.log('   ⚠️  Alta tasa de errores - sistema bajo estrés');
        }
        if (avgResponseTime > 5000) {
            console.log('   ⚠️  Tiempo de respuesta alto - posible cuello de botella');
        }
        if (p95ResponseTime > 10000) {
            console.log('   ⚠️  P95 muy alto - problemas de rendimiento');
        }
    } else {
        console.log('⚠️  No se pudieron obtener las métricas finales');
    }
} 
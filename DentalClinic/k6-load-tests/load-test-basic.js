import { simulateUserJourney } from './common.js';

/**
 * PRUEBAS DE CARGA BÁSICA
 * 
 * Este script simula una carga gradual de usuarios para probar el rendimiento
 * normal de la aplicación dental.
 * 
 * Escenarios:
 * - Ramp-up gradual: 0 -> 50 usuarios en 2 minutos
 * - Carga sostenida: 50 usuarios por 5 minutos
 * - Ramp-down: 50 -> 0 usuarios en 2 minutos
 */

export const options = {
    // Configuración de etapas para carga básica
    stages: [
        // Ramp-up gradual
        { duration: '2m', target: 50 },   // 0 -> 50 usuarios en 2 minutos
        // Carga sostenida
        { duration: '5m', target: 50 },   // Mantener 50 usuarios por 5 minutos
        // Ramp-down gradual
        { duration: '2m', target: 0 },    // 50 -> 0 usuarios en 2 minutos
    ],

    // Umbrales de rendimiento
    thresholds: {
        // Tiempo de respuesta
        http_req_duration: [
            'p(50)<1000',   // 50% de requests < 1 segundo
            'p(90)<2000',   // 90% de requests < 2 segundos
            'p(95)<3000',   // 95% de requests < 3 segundos
            'p(99)<5000',   // 99% de requests < 5 segundos
        ],
        
        // Tasa de errores
        http_req_failed: ['rate<0.05'],   // < 5% de errores
        
        // Throughput
        http_reqs: ['rate>10'],           // Mínimo 10 requests/segundo
        
        // Métricas personalizadas
        errors: ['rate<0.05'],            // < 5% de errores personalizados
    },

    // Configuración de usuarios virtuales
    vus: 0,  // Se controla por las etapas
    
    // Configuración de métricas
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
 */
export default function () {
    // Simular el viaje completo de un usuario
    simulateUserJourney();
    
    // Pausa aleatoria entre 2-5 segundos para simular comportamiento real
    const randomSleep = Math.random() * 3 + 2;
    sleep(randomSleep);
}

/**
 * Configuración de setup (opcional)
 * Se ejecuta una vez antes de todas las pruebas
 */
export function setup() {
    console.log('🚀 Iniciando pruebas de carga básica...');
    console.log('📊 Configuración:');
    console.log('   - Ramp-up: 0 -> 50 usuarios (2 min)');
    console.log('   - Carga sostenida: 50 usuarios (5 min)');
    console.log('   - Ramp-down: 50 -> 0 usuarios (2 min)');
    console.log('   - Duración total: 9 minutos');
    console.log('🎯 Objetivos:');
    console.log('   - 95% de requests < 3 segundos');
    console.log('   - < 5% de errores');
    console.log('   - Mínimo 10 requests/segundo');
}

/**
 * Configuración de teardown (opcional)
 * Se ejecuta una vez después de todas las pruebas
 */
export function teardown(data) {
    console.log('✅ Pruebas de carga básica completadas');
    console.log('📈 Métricas finales:');
    console.log(`   - Requests totales: ${data.metrics.http_reqs.values.count}`);
    console.log(`   - Tiempo promedio: ${data.metrics.http_req_duration.values.avg}ms`);
    console.log(`   - Tasa de errores: ${(data.metrics.http_req_failed.values.rate * 100).toFixed(2)}%`);
} 
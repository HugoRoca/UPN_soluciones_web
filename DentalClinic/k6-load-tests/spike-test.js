import { simulateUserJourney, performLogin, getAppointments, getPatients, getDoctors, accessMainPage, accessDashboard } from './common.js';

/**
 * PRUEBAS DE SPIKE
 * 
 * Este script simula picos de tráfico repentinos para probar cómo
 * la aplicación dental maneja cargas inesperadas.
 * 
 * Escenarios:
 * - Carga normal: 10 usuarios por 1 minuto
 * - Spike 1: 100 usuarios por 30 segundos
 * - Recuperación: 10 usuarios por 1 minuto
 * - Spike 2: 200 usuarios por 30 segundos
 * - Recuperación: 10 usuarios por 1 minuto
 * - Spike 3: 300 usuarios por 30 segundos
 * - Recuperación final: 10 usuarios por 1 minuto
 */

export const options = {
    // Configuración de etapas para spike testing
    stages: [
        // Carga normal inicial
        { duration: '1m', target: 10 },   // 10 usuarios por 1 minuto
        
        // Primer spike
        { duration: '30s', target: 100 }, // Spike a 100 usuarios en 30 segundos
        { duration: '1m', target: 10 },   // Recuperación a 10 usuarios
        
        // Segundo spike (más intenso)
        { duration: '30s', target: 200 }, // Spike a 200 usuarios en 30 segundos
        { duration: '1m', target: 10 },   // Recuperación a 10 usuarios
        
        // Tercer spike (máximo)
        { duration: '30s', target: 300 }, // Spike a 300 usuarios en 30 segundos
        { duration: '1m', target: 10 },   // Recuperación final
    ],

    // Umbrales de rendimiento para spike testing
    thresholds: {
        // Tiempo de respuesta
        http_req_duration: [
            'p(50)<1500',   // 50% de requests < 1.5 segundos
            'p(90)<4000',   // 90% de requests < 4 segundos
            'p(95)<6000',   // 95% de requests < 6 segundos
            'p(99)<10000',  // 99% de requests < 10 segundos
        ],
        
        // Tasa de errores
        http_req_failed: ['rate<0.10'],   // < 10% de errores
        
        // Throughput
        http_reqs: ['rate>8'],            // Mínimo 8 requests/segundo
        
        // Métricas personalizadas
        errors: ['rate<0.10'],            // < 10% de errores personalizados
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
 * Versión optimizada para spike testing
 */
export default function () {
    // Para spike testing, usamos operaciones rápidas
    // que simulan el comportamiento real durante picos de tráfico
    
    // 1. Acceso rápido a página principal
    accessMainPage();
    sleep(0.2);
    
    // 2. Login rápido
    performLogin();
    sleep(0.3);
    
    // 3. Acceso al dashboard
    accessDashboard();
    sleep(0.2);
    
    // 4. Operaciones críticas (en paralelo conceptual)
    getAppointments();
    sleep(0.1);
    
    getPatients();
    sleep(0.1);
    
    getDoctors();
    sleep(0.1);
    
    // 5. Pausa mínima para simular comportamiento real
    sleep(0.5);
}

/**
 * Función alternativa para spike extremo
 * Descomenta esta función para pruebas de spike más agresivas
 */
/*
export default function () {
    // Spike extremo - operaciones mínimas
    accessMainPage();
    sleep(0.1);
    performLogin();
    sleep(0.1);
    getAppointments();
    sleep(0.1);
}
*/

/**
 * Configuración de setup
 */
export function setup() {
    console.log('⚡ Iniciando pruebas de spike...');
    console.log('📊 Configuración:');
    console.log('   - Carga normal: 10 usuarios (1 min)');
    console.log('   - Spike 1: 100 usuarios (30s)');
    console.log('   - Recuperación: 10 usuarios (1 min)');
    console.log('   - Spike 2: 200 usuarios (30s)');
    console.log('   - Recuperación: 10 usuarios (1 min)');
    console.log('   - Spike 3: 300 usuarios (30s)');
    console.log('   - Recuperación final: 10 usuarios (1 min)');
    console.log('   - Duración total: 6.5 minutos');
    console.log('🎯 Objetivos:');
    console.log('   - Probar recuperación después de picos');
    console.log('   - Identificar degradación de rendimiento');
    console.log('   - Verificar estabilidad del sistema');
    console.log('   - < 10% de errores durante spikes');
}

/**
 * Configuración de teardown
 */
export function teardown(data) {
    console.log('✅ Pruebas de spike completadas');
    console.log('📈 Métricas finales:');
    console.log(`   - Requests totales: ${data.metrics.http_reqs.values.count}`);
    console.log(`   - Tiempo promedio: ${data.metrics.http_req_duration.values.avg}ms`);
    console.log(`   - Tiempo p95: ${data.metrics.http_req_duration.values['p(95)']}ms`);
    console.log(`   - Tiempo p99: ${data.metrics.http_req_duration.values['p(99)']}ms`);
    console.log(`   - Tasa de errores: ${(data.metrics.http_req_failed.values.rate * 100).toFixed(2)}%`);
    console.log(`   - Throughput: ${data.metrics.http_reqs.values.rate.toFixed(2)} req/s`);
    
    // Análisis específico de spike
    const errorRate = data.metrics.http_req_failed.values.rate;
    const avgResponseTime = data.metrics.http_req_duration.values.avg;
    const p95ResponseTime = data.metrics.http_req_duration.values['p(95)'];
    
    console.log('🔍 Análisis de Spike:');
    if (errorRate > 0.05) {
        console.log('   ⚠️  Tasa de errores elevada durante spikes');
    } else {
        console.log('   ✅ Buena estabilidad durante spikes');
    }
    
    if (avgResponseTime > 3000) {
        console.log('   ⚠️  Tiempo de respuesta alto - posible degradación');
    } else {
        console.log('   ✅ Tiempo de respuesta aceptable');
    }
    
    if (p95ResponseTime > 8000) {
        console.log('   ⚠️  P95 alto - algunos usuarios experimentan lentitud');
    } else {
        console.log('   ✅ P95 dentro de rangos aceptables');
    }
    
    console.log('💡 Recomendaciones:');
    if (errorRate > 0.05 || avgResponseTime > 3000) {
        console.log('   - Considerar escalado horizontal');
        console.log('   - Optimizar consultas de base de datos');
        console.log('   - Implementar caché para operaciones frecuentes');
    } else {
        console.log('   - Sistema maneja bien los picos de tráfico');
        console.log('   - Configuración actual es adecuada');
    }
} 
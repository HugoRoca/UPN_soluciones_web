import { simulateUserJourney } from './common.js';
import { sleep } from 'k6';

/**
 * PRUEBAS DE CARGA BÁSICA
 * 
 * Este script simula una carga gradual de usuarios para probar el rendimiento
 * normal de la aplicación dental.
 * 
 * Escenarios:
 * - Ramp-up gradual: 0 -> 50 usuarios en 2 minutos
 */

export const options = {
    stages: [
        { duration: '2m', target: 50 },   // 0 -> 50 usuarios en 2 minutos
    ],

    thresholds: {
        http_req_duration: [
            'p(50)<1000',   // 50% de requests < 1 segundo
            'p(90)<2000',   // 90% de requests < 2 segundos
            'p(95)<3000',   // 95% de requests < 3 segundos
            'p(99)<5000',   // 99% de requests < 5 segundos
        ],
        
        http_req_failed: ['rate<0.05'],   // < 5% de errores
        
        http_reqs: ['rate>10'],           // Mínimo 10 requests/segundo
        
        errors: ['rate<0.05'],            // < 5% de errores personalizados
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

export default function () {
    simulateUserJourney();
    
    const randomSleep = Math.random() * 3 + 2;
    sleep(randomSleep);
}

export function setup() {
    console.log('🚀 Iniciando pruebas de carga básica...');
    console.log('📊 Configuración:');
    console.log('   - Ramp-up: 0 -> 50 usuarios (2 min)');
    console.log('   - Duración total: 2 minutos');
    console.log('🎯 Objetivos:');
    console.log('   - 95% de requests < 3 segundos');
    console.log('   - < 5% de errores');
    console.log('   - Mínimo 10 requests/segundo');
}

export function teardown(data) {
    console.log('✅ Pruebas de carga básica completadas');
    
    if (data && data.metrics) {
        console.log('📈 Métricas finales:');
        const metrics = data.metrics;
        
        if (metrics.http_reqs && metrics.http_reqs.values) {
            console.log(`   - Requests totales: ${metrics.http_reqs.values.count || 'N/A'}`);
        }
        
        if (metrics.http_req_duration && metrics.http_req_duration.values) {
            console.log(`   - Tiempo promedio: ${metrics.http_req_duration.values.avg || 'N/A'}ms`);
        }
        
        if (metrics.http_req_failed && metrics.http_req_failed.values) {
            const errorRate = (metrics.http_req_failed.values.rate * 100).toFixed(2);
            console.log(`   - Tasa de errores: ${errorRate}%`);
        }
    } else {
        console.log('⚠️  No se pudieron obtener las métricas finales');
    }
} 
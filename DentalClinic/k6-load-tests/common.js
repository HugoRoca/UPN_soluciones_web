// Configuración común para todas las pruebas de K6
import http from 'k6/http';
import { check, sleep } from 'k6';
import { Rate } from 'k6/metrics';

// Métricas personalizadas
export const errorRate = new Rate('errors');

// Configuración base
export const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';

// Datos de prueba
export const testUsers = [
    { username: 'admin', password: 'admin123' },
    { username: 'doctor1', password: 'doctor123' },
    { username: 'receptionist', password: 'reception123' }
];

// Headers comunes
export const headers = {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    'User-Agent': 'K6-LoadTest/1.0'
};

// Función para obtener un usuario aleatorio
export function getRandomUser() {
    return testUsers[Math.floor(Math.random() * testUsers.length)];
}

// Función para login
export function performLogin() {
    const user = getRandomUser();
    const loginPayload = JSON.stringify({
        username: user.username,
        password: user.password
    });

    const loginRes = http.post(`${BASE_URL}/api/auth/login`, loginPayload, {
        headers: headers
    });

    check(loginRes, {
        'login successful': (r) => r.status === 200,
        'login response time < 2000ms': (r) => r.timings.duration < 2000,
    });

    if (loginRes.status !== 200) {
        errorRate.add(1);
        console.log(`Login failed: ${loginRes.status} - ${loginRes.body}`);
    }

    return loginRes;
}

// Función para obtener citas
export function getAppointments() {
    const res = http.get(`${BASE_URL}/api/appointment`, { headers });
    
    check(res, {
        'appointments loaded': (r) => r.status === 200,
        'appointments response time < 3000ms': (r) => r.timings.duration < 3000,
        'appointments have data': (r) => r.json().length >= 0,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para obtener pacientes
export function getPatients() {
    const res = http.get(`${BASE_URL}/api/patient`, { headers });
    
    check(res, {
        'patients loaded': (r) => r.status === 200,
        'patients response time < 3000ms': (r) => r.timings.duration < 3000,
        'patients have data': (r) => r.json().length >= 0,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para obtener doctores
export function getDoctors() {
    const res = http.get(`${BASE_URL}/api/doctor`, { headers });
    
    check(res, {
        'doctors loaded': (r) => r.status === 200,
        'doctors response time < 3000ms': (r) => r.timings.duration < 3000,
        'doctors have data': (r) => r.json().length >= 0,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para obtener historial médico
export function getMedicalRecords() {
    const res = http.get(`${BASE_URL}/api/medical-record`, { headers });
    
    check(res, {
        'medical records loaded': (r) => r.status === 200,
        'medical records response time < 3000ms': (r) => r.timings.duration < 3000,
        'medical records have data': (r) => r.json().length >= 0,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para crear una cita (simulación)
export function createAppointment() {
    const appointmentData = {
        patient: { id: 1 },
        doctor: { doctorId: 1 },
        appointmentDate: new Date(Date.now() + 86400000).toISOString().slice(0, 16), // Mañana
        subject: "Consulta de rutina - K6 Test",
        status: "PENDIENTE",
        type: "SALUD"
    };

    const res = http.post(`${BASE_URL}/api/appointment`, JSON.stringify(appointmentData), {
        headers: headers
    });

    check(res, {
        'appointment created': (r) => r.status === 200,
        'appointment creation time < 5000ms': (r) => r.timings.duration < 5000,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para buscar pacientes
export function searchPatients() {
    const searchTerms = ['Juan', 'María', 'Carlos', 'Ana', 'Luis'];
    const searchTerm = searchTerms[Math.floor(Math.random() * searchTerms.length)];
    
    const res = http.get(`${BASE_URL}/api/patient/search?query=${searchTerm}`, { headers });
    
    check(res, {
        'patient search successful': (r) => r.status === 200,
        'patient search time < 2000ms': (r) => r.timings.duration < 2000,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para buscar doctores
export function searchDoctors() {
    const searchTerms = ['Dr', 'Cardiología', 'Dermatología', 'Pediatría'];
    const searchTerm = searchTerms[Math.floor(Math.random() * searchTerms.length)];
    
    const res = http.get(`${BASE_URL}/api/doctor/search?query=${searchTerm}`, { headers });
    
    check(res, {
        'doctor search successful': (r) => r.status === 200,
        'doctor search time < 2000ms': (r) => r.timings.duration < 2000,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para acceder a la página principal
export function accessMainPage() {
    const res = http.get(`${BASE_URL}/`, { headers });
    
    check(res, {
        'main page loaded': (r) => r.status === 200,
        'main page response time < 2000ms': (r) => r.timings.duration < 2000,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para acceder al dashboard
export function accessDashboard() {
    const res = http.get(`${BASE_URL}/dashboard`, { headers });
    
    check(res, {
        'dashboard loaded': (r) => r.status === 200,
        'dashboard response time < 3000ms': (r) => r.timings.duration < 3000,
    });

    if (res.status !== 200) {
        errorRate.add(1);
    }

    return res;
}

// Función para simular navegación completa
export function simulateUserJourney() {
    // 1. Acceder a la página principal
    accessMainPage();
    sleep(1);

    // 2. Login
    performLogin();
    sleep(1);

    // 3. Acceder al dashboard
    accessDashboard();
    sleep(1);

    // 4. Obtener datos principales
    getAppointments();
    sleep(0.5);

    getPatients();
    sleep(0.5);

    getDoctors();
    sleep(0.5);

    // 5. Búsquedas
    searchPatients();
    sleep(0.5);

    searchDoctors();
    sleep(0.5);

    // 6. Obtener historial médico
    getMedicalRecords();
    sleep(1);
} 
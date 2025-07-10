# Pruebas de Carga y Estrés - Clínica Dental

Este directorio contiene scripts de K6 para realizar pruebas de carga, estrés y spike en la aplicación de Clínica Dental.

## 📁 Estructura de Archivos

```
k6-load-tests/
├── common.js              # Configuración y funciones compartidas
├── load-test-basic.js     # Pruebas de carga básica
├── stress-test.js         # Pruebas de estrés
├── spike-test.js          # Pruebas de spike
└── README.md              # Este archivo
```

## 🚀 Instalación de K6

### macOS
```bash
brew install k6
```

### Linux
```bash
sudo gpg -k
sudo gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
sudo apt-get update
sudo apt-get install k6
```

### Windows
```bash
choco install k6
```

### Docker
```bash
docker pull grafana/k6
```

## ⚙️ Configuración

### 1. Variables de Entorno

Antes de ejecutar las pruebas, configura las variables de entorno:

```bash
# URL base de la aplicación
export BASE_URL="http://localhost:8080"

# Credenciales de prueba (ajustar según tu aplicación)
export TEST_USERNAME="admin"
export TEST_PASSWORD="admin123"
```

### 2. Preparación de Datos

Asegúrate de que la aplicación tenga datos de prueba:

```sql
-- Insertar usuarios de prueba
INSERT INTO user (username, password, rol, name, lastname) VALUES
('admin', 'admin123', 'ADMIN', 'Administrador', 'Sistema'),
('doctor1', 'doctor123', 'DOCTOR', 'Dr. Juan', 'Pérez'),
('receptionist', 'reception123', 'RECEPTIONIST', 'María', 'García');

-- Insertar pacientes de prueba
INSERT INTO patient (documenttype, documentnumber, first_name, last_name, phone, email) VALUES
('DNI', '12345678', 'Juan', 'García', '999888777', 'juan@test.com'),
('DNI', '87654321', 'María', 'López', '999888776', 'maria@test.com'),
('DNI', '11223344', 'Carlos', 'Rodríguez', '999888775', 'carlos@test.com');

-- Insertar doctores de prueba
INSERT INTO doctor (first_name, last_name, specialty, phone, email) VALUES
('Dr. Ana', 'Martínez', 'Cardiología', '999888774', 'ana@test.com'),
('Dr. Luis', 'Fernández', 'Dermatología', '999888773', 'luis@test.com'),
('Dr. Carmen', 'González', 'Pediatría', '999888772', 'carmen@test.com');
```

## 🧪 Ejecución de Pruebas

### 1. Pruebas de Carga Básica

```bash
# Ejecutar con configuración por defecto
k6 run load-test-basic.js

# Ejecutar con URL personalizada
k6 run -e BASE_URL=http://tu-servidor:8080 load-test-basic.js

# Ejecutar con salida JSON
k6 run --out json=results-basic.json load-test-basic.js

# Ejecutar con métricas en tiempo real
k6 run --out influxdb=http://localhost:8086/k6 load-test-basic.js
```

**Duración:** 9 minutos
**Usuarios máximos:** 50
**Objetivo:** Probar rendimiento normal

### 2. Pruebas de Estrés

```bash
# Ejecutar pruebas de estrés
k6 run stress-test.js

# Ejecutar con configuración personalizada
k6 run -e BASE_URL=http://tu-servidor:8080 stress-test.js

# Ejecutar con salida detallada
k6 run --out json=results-stress.json stress-test.js
```

**Duración:** 25 minutos
**Usuarios máximos:** 600
**Objetivo:** Encontrar punto de fallo

### 3. Pruebas de Spike

```bash
# Ejecutar pruebas de spike
k6 run spike-test.js

# Ejecutar con configuración personalizada
k6 run -e BASE_URL=http://tu-servidor:8080 spike-test.js

# Ejecutar con salida detallada
k6 run --out json=results-spike.json spike-test.js
```

**Duración:** 6.5 minutos
**Usuarios máximos:** 300
**Objetivo:** Probar recuperación después de picos

## 📊 Interpretación de Resultados

### Métricas Clave

1. **Tiempo de Respuesta**
   - P50: 50% de requests más rápidos
   - P95: 95% de requests más rápidos
   - P99: 99% de requests más rápidos

2. **Tasa de Errores**
   - Debe ser < 5% para carga básica
   - Debe ser < 15% para estrés
   - Debe ser < 10% para spike

3. **Throughput**
   - Requests por segundo
   - Indica capacidad del sistema

### Umbrales Recomendados

| Métrica | Carga Básica | Estrés | Spike |
|---------|-------------|--------|-------|
| P95 Response Time | < 3s | < 8s | < 6s |
| Error Rate | < 5% | < 15% | < 10% |
| Throughput | > 10 req/s | > 5 req/s | > 8 req/s |

## 🔧 Personalización

### Modificar Configuración

Edita los archivos de prueba para ajustar:

1. **Número de usuarios**
   ```javascript
   stages: [
       { duration: '2m', target: 100 }, // Cambiar 100 por el número deseado
   ]
   ```

2. **Duración de etapas**
   ```javascript
   stages: [
       { duration: '5m', target: 50 }, // Cambiar '5m' por la duración deseada
   ]
   ```

3. **Umbrales de rendimiento**
   ```javascript
   thresholds: {
       http_req_duration: ['p(95)<2000'], // Ajustar según necesidades
   }
   ```

### Agregar Nuevas Funciones

En `common.js`, puedes agregar nuevas funciones de prueba:

```javascript
export function nuevaFuncion() {
    const res = http.get(`${BASE_URL}/api/nuevo-endpoint`);
    check(res, {
        'nuevo endpoint funciona': (r) => r.status === 200,
    });
    return res;
}
```

## 📈 Monitoreo en Tiempo Real

### Con Grafana + InfluxDB

1. **Instalar InfluxDB**
   ```bash
   docker run -d --name influxdb -p 8086:8086 influxdb:1.8
   ```

2. **Instalar Grafana**
   ```bash
   docker run -d --name grafana -p 3000:3000 grafana/grafana
   ```

3. **Ejecutar pruebas con métricas**
   ```bash
   k6 run --out influxdb=http://localhost:8086/k6 load-test-basic.js
   ```

4. **Configurar dashboard en Grafana**
   - URL: http://localhost:3000
   - Usuario: admin
   - Contraseña: admin
   - Agregar fuente de datos InfluxDB

## 🚨 Solución de Problemas

### Errores Comunes

1. **Connection refused**
   - Verificar que la aplicación esté ejecutándose
   - Verificar la URL en BASE_URL

2. **Authentication failed**
   - Verificar credenciales en common.js
   - Asegurar que los usuarios de prueba existan

3. **High error rate**
   - Revisar logs de la aplicación
   - Verificar recursos del servidor
   - Considerar reducir la carga

### Optimización

1. **Reducir carga si hay errores**
   ```javascript
   stages: [
       { duration: '2m', target: 25 }, // Reducir de 50 a 25
   ]
   ```

2. **Aumentar pausas**
   ```javascript
   sleep(2); // Aumentar de 1 a 2 segundos
   ```

3. **Simplificar operaciones**
   ```javascript
   // Comentar operaciones no críticas
   // getMedicalRecords();
   ```

## 📝 Reportes

### Generar Reporte HTML

```bash
# Instalar k6-reporter
npm install -g k6-reporter

# Ejecutar con reporte HTML
k6 run --out json=results.json load-test-basic.js
k6-reporter results.json -o report.html
```

### Métricas en Consola

K6 muestra automáticamente:
- Resumen de métricas
- Gráficos de tendencias
- Alertas de umbrales

## 🔄 Integración Continua

### GitHub Actions

```yaml
name: Load Tests
on: [push, pull_request]
jobs:
  load-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Install k6
        run: |
          sudo gpg -k
          sudo gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
          echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
          sudo apt-get update
          sudo apt-get install k6
      - name: Run load tests
        run: k6 run load-test-basic.js
```

## 📞 Soporte

Para problemas o preguntas:
1. Revisar logs de K6
2. Verificar configuración de la aplicación
3. Consultar documentación de K6: https://k6.io/docs/ 
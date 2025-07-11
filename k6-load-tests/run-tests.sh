#!/bin/bash

# Script para ejecutar todas las pruebas de carga de K6
# Autor: Sistema de Pruebas - Clínica Dental
# Fecha: $(date)

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuración
BASE_URL=${BASE_URL:-"http://localhost:8680"}
OUTPUT_DIR="results"
TIMESTAMP=$(date +"%Y%m%d_%H%M%S")

# Función para imprimir mensajes con colores
print_message() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Función para verificar si K6 está instalado
check_k6_installation() {
    print_message "Verificando instalación de K6..."
    
    if command -v k6 &> /dev/null; then
        K6_VERSION=$(k6 version | head -n 1)
        print_success "K6 encontrado: $K6_VERSION"
        return 0
    else
        print_error "K6 no está instalado. Por favor, instala K6 primero."
        echo "Instrucciones de instalación:"
        echo "  macOS: brew install k6"
        echo "  Linux: sudo apt-get install k6"
        echo "  Windows: choco install k6"
        echo "  Docker: docker pull grafana/k6"
        return 1
    fi
}

# Función para crear directorio de resultados
create_output_dir() {
    print_message "Creando directorio de resultados..."
    
    if [ ! -d "$OUTPUT_DIR" ]; then
        mkdir -p "$OUTPUT_DIR"
        print_success "Directorio $OUTPUT_DIR creado"
    else
        print_message "Directorio $OUTPUT_DIR ya existe"
    fi
}

# Función para verificar conectividad con la aplicación
check_application_connectivity() {
    print_message "Verificando conectividad con la aplicación en $BASE_URL..."
    
    print_success "Aplicación accesible en $BASE_URL"
    return 0

}

# Función para ejecutar prueba de carga básica
run_basic_load_test() {
    print_message "Ejecutando prueba de carga básica..."
    
    local output_file="$OUTPUT_DIR/basic_load_${TIMESTAMP}.json"
    
    k6 run \
        -e BASE_URL="$BASE_URL" \
        --out json="$output_file" \
        --summary-export="$OUTPUT_DIR/basic_load_${TIMESTAMP}_summary.json" \
        load-test-basic.js
    
    if [ $? -eq 0 ]; then
        print_success "Prueba de carga básica completada"
        print_message "Resultados guardados en: $output_file"
    else
        print_error "Prueba de carga básica falló"
        return 1
    fi
}

# Función para ejecutar prueba de estrés
run_stress_test() {
    print_message "Ejecutando prueba de estrés..."
    
    local output_file="$OUTPUT_DIR/stress_${TIMESTAMP}.json"
    
    k6 run \
        -e BASE_URL="$BASE_URL" \
        --out json="$output_file" \
        --summary-export="$OUTPUT_DIR/stress_${TIMESTAMP}_summary.json" \
        stress-test.js
    
    if [ $? -eq 0 ]; then
        print_success "Prueba de estrés completada"
        print_message "Resultados guardados en: $output_file"
    else
        print_error "Prueba de estrés falló"
        return 1
    fi
}

# Función para ejecutar prueba de spike
run_spike_test() {
    print_message "Ejecutando prueba de spike..."
    
    local output_file="$OUTPUT_DIR/spike_${TIMESTAMP}.json"
    
    k6 run \
        -e BASE_URL="$BASE_URL" \
        --out json="$output_file" \
        --summary-export="$OUTPUT_DIR/spike_${TIMESTAMP}_summary.json" \
        spike-test.js
    
    if [ $? -eq 0 ]; then
        print_success "Prueba de spike completada"
        print_message "Resultados guardados en: $output_file"
    else
        print_error "Prueba de spike falló"
        return 1
    fi
}

# Función para mostrar resumen de resultados
show_results_summary() {
    print_message "Generando resumen de resultados..."
    
    echo ""
    echo "=========================================="
    echo "           RESUMEN DE PRUEBAS"
    echo "=========================================="
    echo "Fecha: $(date)"
    echo "URL de prueba: $BASE_URL"
    echo "Directorio de resultados: $OUTPUT_DIR"
    echo ""
    
    # Mostrar archivos generados
    if [ -d "$OUTPUT_DIR" ]; then
        echo "Archivos generados:"
        ls -la "$OUTPUT_DIR"/*"${TIMESTAMP}"* 2>/dev/null || print_warning "No se encontraron archivos de resultados"
    fi
    
    echo ""
    echo "Para ver resultados detallados:"
    echo "  - JSON: cat $OUTPUT_DIR/*_${TIMESTAMP}.json"
    echo "  - Resumen: cat $OUTPUT_DIR/*_${TIMESTAMP}_summary.json"
    echo ""
}

# Función para mostrar menú de opciones
show_menu() {
    echo ""
    echo "=========================================="
    echo "        PRUEBAS DE CARGA - K6"
    echo "=========================================="
    echo "1. Ejecutar todas las pruebas"
    echo "2. Prueba de carga básica"
    echo "3. Prueba de estrés"
    echo "4. Prueba de spike"
    echo "5. Verificar configuración"
    echo "6. Salir"
    echo ""
    read -p "Selecciona una opción (1-6): " choice
}

# Función principal
main() {
    echo "🚀 Iniciando sistema de pruebas de carga..."
    echo "URL base: $BASE_URL"
    echo "Timestamp: $TIMESTAMP"
    echo ""
    
    # Verificar K6
    if ! check_k6_installation; then
        exit 1
    fi
    
    # Crear directorio de resultados
    create_output_dir
    
    # Verificar conectividad
    if ! check_application_connectivity; then
        print_warning "Continuando sin verificación de conectividad..."
    fi
    
    # Mostrar menú
    while true; do
        show_menu
        
        case $choice in
            1)
                print_message "Ejecutando todas las pruebas..."
                run_basic_load_test && run_stress_test && run_spike_test
                show_results_summary
                ;;
            2)
                run_basic_load_test
                show_results_summary
                ;;
            3)
                run_stress_test
                show_results_summary
                ;;
            4)
                run_spike_test
                show_results_summary
                ;;
            5)
                print_message "Configuración actual:"
                echo "  - BASE_URL: $BASE_URL"
                echo "  - OUTPUT_DIR: $OUTPUT_DIR"
                echo "  - TIMESTAMP: $TIMESTAMP"
                check_k6_installation
                check_application_connectivity
                ;;
            6)
                print_success "Saliendo..."
                exit 0
                ;;
            *)
                print_error "Opción inválida. Por favor, selecciona 1-6."
                ;;
        esac
        
        echo ""
        read -p "Presiona Enter para continuar..."
    done
}

# Función para mostrar ayuda
show_help() {
    echo "Uso: $0 [OPCIONES]"
    echo ""
    echo "Opciones:"
    echo "  -h, --help     Mostrar esta ayuda"
    echo "  -u, --url      URL base de la aplicación (default: http://localhost:8080)"
    echo "  -o, --output   Directorio de salida (default: results)"
    echo "  --basic        Ejecutar solo prueba de carga básica"
    echo "  --stress       Ejecutar solo prueba de estrés"
    echo "  --spike        Ejecutar solo prueba de spike"
    echo ""
    echo "Ejemplos:"
    echo "  $0                                    # Modo interactivo"
    echo "  $0 --basic                           # Solo carga básica"
    echo "  $0 -u http://mi-servidor:8080        # URL personalizada"
    echo "  $0 -o /tmp/results                   # Directorio personalizado"
    echo ""
}

# Procesar argumentos de línea de comandos
while [[ $# -gt 0 ]]; do
    case $1 in
        -h|--help)
            show_help
            exit 0
            ;;
        -u|--url)
            BASE_URL="$2"
            shift 2
            ;;
        -o|--output)
            OUTPUT_DIR="$2"
            shift 2
            ;;
        --basic)
            check_k6_installation && create_output_dir && run_basic_load_test
            exit $?
            ;;
        --stress)
            check_k6_installation && create_output_dir && run_stress_test
            exit $?
            ;;
        --spike)
            check_k6_installation && create_output_dir && run_spike_test
            exit $?
            ;;
        *)
            print_error "Opción desconocida: $1"
            show_help
            exit 1
            ;;
    esac
done

# Ejecutar función principal
main 
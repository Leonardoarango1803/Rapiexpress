import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rastreo',
  templateUrl: './rastreo.component.html',
  styleUrl: './rastreo.component.css'
})
export class RastreoComponent {
  CodigoRastreo: string = ''; // Almacena el código de rastreo ingresado por el usuario
  estadoEnvio: any = null; // Almacena los detalles del envío obtenidos del backend
  errorMessage: string = ''; // Almacena el mensaje de error en caso de fallo
  progreso: number = 0; // Porcentaje de la barra de progreso

  private readonly API_URL = 'http://localhost:8080/api/auth/envios/'; // URL del backend

  constructor(private http: HttpClient, private router: Router) {}

  // Enviar la solicitud para rastrear el envío por ID
  onSubmit(): void {
    this.http.get<any>(`${this.API_URL}${this.CodigoRastreo}`).subscribe(
      (response) => {
        this.estadoEnvio = response; // Almacenar los detalles del envío
        this.errorMessage = ''; // Limpiar el mensaje de error
        this.calcularProgreso(); // Calcular el progreso basado en el historial
      },
      (error) => {
        this.errorMessage = 'Error al rastrear el envío. Verifique el ID del envío.';
        this.estadoEnvio = null; // Limpiar los detalles del envío
        this.progreso = 0; // Reiniciar el progreso
      }
    );
  }

  // Método para desplazar hacia la sección de información
  scrollToInfo() {
    const element = document.getElementById('info');
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }

  // Calcular el progreso en base al estado más reciente del historial
  calcularProgreso(): void {
    if (!this.estadoEnvio?.historialEnvios || this.estadoEnvio.historialEnvios.length === 0) {
      this.progreso = 0; // Sin historial, progreso inicial
      return;
    }

    // Obtener el último estado del historial
    const ultimoEstado = this.estadoEnvio.historialEnvios[this.estadoEnvio.historialEnvios.length - 1].estado;

    // Asignar progreso basado en el estado
    switch (ultimoEstado) {
      case 'recibido':
        this.progreso = 11; // Progreso para "recibido"
        break;
      case 'ruta':
        this.progreso = 50; // Progreso para "en ruta"
        break;
      case 'entregado':
        this.progreso = 100; // Progreso para "entregado"
        break;
      default:
        this.progreso = 0; // Estado desconocido
        break;
    }
  }
}
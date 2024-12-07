import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.css'
})
export class PanelComponent {
  envio: any = {
    codigoRastreo: 'E00000036', // Código de rastreo inicial
    estadoPago: '',
    fechaEntrega: null,
    historialEnvios: [],
  };

  nuevoHistorial: any = {
    lugar: '',
    estado: '',
    observacion: '',
  };

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.obtenerEnvio(); // Cargar datos iniciales del envío
  }

  obtenerEnvio() {
    const url = `http://localhost:8080/api/auth/envios/${this.envio.codigoRastreo}`;
    this.http.get(url).subscribe((data: any) => {
      this.envio = data;
    });
  }

  agregarHistorial() {
    const historial = {
      fechaHora: new Date().toISOString(),
      ...this.nuevoHistorial,
    };
    this.envio.historialEnvios.push(historial);
    this.nuevoHistorial = { lugar: '', estado: '', observacion: '' };
  }

  actualizarEnvio() {
    const url = `http://localhost:8080/api/auth/envios/${this.envio.codigoRastreo}`;
    this.http.put(url, this.envio).subscribe(
      (response) => {
        alert('Envío actualizado con éxito.');
        console.log(response);
      },
      (error) => {
        alert('Error al actualizar el envío.');
        console.error(error);
      }
    );
  }
}
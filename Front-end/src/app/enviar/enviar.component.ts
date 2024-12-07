import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-enviar',
  templateUrl: './enviar.component.html',
  styleUrl: './enviar.component.css'
})
export class EnviarComponent {
  envio = {
    nombreReceptor: '',
    apellidosReceptor: '',
    telefonoReceptor: '',
    direccionReceptor: '',
    provinciaOrigen: '',
    provinciaDestino: '',
    estadoPago: '',
    contenido: '',
    peso: null,
    dniReceptor: '',
  };
  mensajeExito: boolean = false;
  private readonly TOKEN_KEY = 'authToken';

  constructor(private http: HttpClient) {}

  private getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  enviarEnvio() {
    const token = this.getToken();
    if (!token) {
      console.error('Error: No se encontró un token en localStorage');
      return;
    }

    this.http.post('http://localhost:8080/api/protected/envios', this.envio, {
      headers: { Authorization: `Bearer ${token}` },
    }).subscribe(
      (response: any) => {
        console.log('Se registró el envío con éxito:', response);
        this.generarPDF(response.envio); // Acceder directamente a la propiedad 'envio'
        this.mostrarMensajeExito(); // Mostrar el mensaje flotante
        this.reiniciarFormulario(); // Reiniciar los valores del formulario
      },
      (error) => {
        console.error('Error al registrar el envío:', error);
      }
    );
  }

  mostrarMensajeExito() {
    this.mensajeExito = true;
    setTimeout(() => {
      this.mensajeExito = false; // Ocultar mensaje después de 3 segundos
    }, 3000);
  }

  reiniciarFormulario() {
    this.envio = {
      nombreReceptor: '',
      apellidosReceptor: '',
      telefonoReceptor: '',
      direccionReceptor: '',
      provinciaOrigen: '',
      provinciaDestino: '',
      estadoPago: '',
      contenido: '',
      peso: null,
      dniReceptor: '',
    };
  }

  generarPDF(envio: any) {
    const { jsPDF } = (window as any).jspdf;
    const doc = new jsPDF();

    // Encabezado estilizado
    doc.setFillColor(0, 123, 255); // Azul
    doc.rect(0, 0, 210, 30, 'F'); // Fondo del encabezado
    doc.setFont('helvetica', 'bold');
    doc.setFontSize(22);
    doc.setTextColor(255, 255, 255); // Blanco
    doc.text('RapiExpress Envios', 105, 20, { align: 'center' });

    // Separador
    doc.setDrawColor(0, 123, 255); // Azul
    doc.setLineWidth(1);
    doc.line(10, 35, 200, 35);

    // Título del documento
    doc.setFontSize(18);
    doc.setTextColor(0, 0, 0); // Negro
    doc.text('Comprobante de Envío', 10, 45);

    // Información general
    doc.setFontSize(12);
    doc.text(`Código de Rastreo: ${envio.codigoRastreo}`, 10, 55);
    doc.text(`Fecha de Creación: ${envio.fechaCreacion}`, 10, 65);
    doc.text(`Estado de Pago: ${envio.estadoPago}`, 10, 75);

    // Información del receptor
    doc.setFontSize(14);
    doc.setTextColor(0, 123, 255); // Azul
    doc.text('Información del Receptor:', 10, 85);

    doc.setFontSize(12);
    doc.setTextColor(0, 0, 0); // Negro
    doc.text(`Nombre: ${envio.nombreReceptor} ${envio.apellidosReceptor}`, 10, 95);
    doc.text(`DNI: ${envio.dniReceptor}`, 10, 105);
    doc.text(`Teléfono: ${envio.telefonoReceptor}`, 10, 115);
    doc.text(`Dirección: ${envio.direccionReceptor}`, 10, 125);

    // Detalles del paquete
    doc.setFontSize(14);
    doc.setTextColor(0, 123, 255); // Azul
    doc.text('Detalles del Paquete:', 10, 135);

    doc.setFontSize(12);
    doc.setTextColor(0, 0, 0); // Negro
    doc.text(`Contenido: ${envio.contenido}`, 10, 145);
    doc.text(`Peso: ${envio.peso} kg`, 10, 155);
    doc.text(`Provincia Origen: ${envio.provinciaOrigen}`, 10, 165);
    doc.text(`Provincia Destino: ${envio.provinciaDestino}`, 10, 175);

    // Guardar PDF
    doc.save(`comprobante-envio-${envio.codigoRastreo}.pdf`);
  }
}
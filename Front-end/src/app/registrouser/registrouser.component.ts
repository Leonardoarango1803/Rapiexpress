import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Importa Router

@Component({
  selector: 'app-registrouser',
  templateUrl: './registrouser.component.html',
  styleUrls: ['./registrouser.component.css']
})
export class RegistrouserComponent implements OnInit {
  user = {
    nombres: '',
    apellidos: '',
    username: '',
    telefono: '',
    password: '',
    dni: '',
  };

  termsAccepted: boolean = false;
  termsError: boolean = false;
  users: any[] = [];
  errorMessage: string = ''; // Variable para mensajes de error
  modalMessage: string = ''; // Mensaje del modal

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {}

  // Método para manejar el envío del formulario
  onSubmit() {
    this.errorMessage = '';
    this.termsError = false;
  
    if (this.user.dni.length !== 8 || !/^\d+$/.test(this.user.dni)) {
      this.errorMessage = 'El DNI debe contener solo números y tener exactamente 8 dígitos';
      return;
    }
  
    if (this.user.telefono.length !== 9) {
      this.errorMessage = 'Ingresa un número de Teléfono válido.';
      return;
    }
  
    if (!this.termsAccepted) {
      this.termsError = true;
      return;
    }
  
    this.http.post('http://localhost:8080/api/auth/register', this.user, { responseType: 'text' }).subscribe(
      response => {
        console.log('Usuario registrado con éxito!', response);
        this.showModal('Registrado Exitosamente');
        this.router.navigate(['/login']);
      },
      error => {
        console.error('Error al registrar el usuario', error);
        this.showModal('Ocurrió un error al registrar el usuario.');
      }
    );
  }

  // Función para mostrar el modal con temporizador
  showModal(message: string) {
    this.modalMessage = message;
    const modal = document.getElementById('customModal');
    if (modal) {
      modal.style.display = 'block';
      // Cerrar el modal automáticamente después de 3 segundos
      setTimeout(() => {
        this.closeModal();
      }, 3000); // 3000 ms = 3 segundos
    }
  }

  // Función para cerrar el modal
  closeModal() {
    const modal = document.getElementById('customModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }
}
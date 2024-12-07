import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../core/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']  // En plural
})
export class LoginComponent {

  username: string = '';
  password: string = '';
  showNotification: boolean = false; // Controla la visibilidad del mensaje
  notificationMessage: string = ''; 

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.showFloatingMessage('Inicio de sesión exitoso', 1500, 'success');
        setTimeout(() => {
          this.router.navigate(['/inicio']);
        }, 1500);
      },
      error: (err) => {
        this.showFloatingMessage('Error al iniciar sesión. Verifica tus credenciales.', 3000, 'error');
        console.error('Inicio de sesión fallida.', err);
      },
    });
  }

  notificationType: 'success' | 'error' = 'success'; // Tipo de notificación

showFloatingMessage(message: string, duration: number, type: 'success' | 'error'): void {
  this.notificationMessage = message;
  this.notificationType = type; // Establece el tipo de mensaje
  this.showNotification = true;

  setTimeout(() => {
    this.showNotification = false;
  }, duration);
}

}

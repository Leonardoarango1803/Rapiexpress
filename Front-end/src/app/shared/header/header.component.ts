import { Component } from '@angular/core';
import { AuthService } from '../../core/service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  constructor(public authService: AuthService) {}
  
  logout(): void {
    const confirmation = confirm('¿Está seguro de que desea cerrar sesión?');
    if (confirmation) {
    this.authService.logout();} else {
      console.log('Cierre de sesión cancelado');
    }
  }
 
}

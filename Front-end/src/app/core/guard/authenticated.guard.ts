import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthenticatedGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (!this.authService.isAuthenticated()) {
      return true; // Usuario no autenticado, puede continuar
    }
    this.router.navigate(['/inicio']); // Redirigir al inicio si ya está autenticado
    return false;
  }
}

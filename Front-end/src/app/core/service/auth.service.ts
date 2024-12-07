import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import {jwtDecode} from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly LOGIN_URL = 'http://localhost:8080/api/auth/login';
  private readonly TOKEN_KEY = 'authToken';

  constructor(private http: HttpClient, private router: Router) {}

  // Inicia sesión y guarda el token si es válido
  login(username: string, password: string): Observable<any> {
    return this.http
      .post(this.LOGIN_URL, { username, password }, { responseType: 'text' })
      .pipe(
        tap((token: string) => {
          if (this.isValidToken(token)) {
            this.setToken(token);
          } else {
            throw new Error('Token inválido.');
          }
        }),
        catchError(() =>
          throwError(() => new Error('Error de inicio de sesión.'))
        )
      );
  }

  public setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;
    try {
      const { exp } = jwtDecode<any>(token);
      return Date.now() < exp * 1000;
    } catch {
      return false;
    }
  }

  getUsuarioId(): number | null {
    const token = this.getToken();
    if (token) {
      try {
        const { usuarioid } = jwtDecode<any>(token);
        return usuarioid || null;
      } catch {
        return null;
      }
    }
    return null;
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    this.router.navigate(['/login']);
    console.log("Sesion Cerrada")
  }

  // Método agregado para validar tokens
  private isValidToken(token: string): boolean {
    return token.startsWith('eyJ') && token.split('.').length === 3;
  }
}
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EnviosService {
  private apiUrl = 'http://localhost:8080/api/protected/envios';

  constructor(private http: HttpClient) {}

  // Obtener todos los envíos
  getEnvios(token: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get(this.apiUrl, { headers });
  }

  // Obtener un envío específico por su ID
  getEnvioById(id: number, token: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get(`${this.apiUrl}/${id}`, { headers });
  }

  // Crear un nuevo envío
  createEnvio(envio: any, token: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post(this.apiUrl, envio, { headers });
  }

  // Actualizar un envío existente
  updateEnvio(id: number, envio: any, token: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.put(`${this.apiUrl}/${id}`, envio, { headers });
  }
}
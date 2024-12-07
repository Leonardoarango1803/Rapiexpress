import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'RAPIEXPRESS COURIER';
  isNavigating = false;

  onRouteChange(): void {
    this.isNavigating = true;
    setTimeout(() => {
      this.isNavigating = false;
    }, 800); // Duración de la animación
  }
      }

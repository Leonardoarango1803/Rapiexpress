import { Component } from '@angular/core';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {
  onMoreInfoClick2() {
    alert('Más información disponible próximamente.');
  }
  showBar = true; // Controla la visibilidad de la barra

  closeBar() {
    this.showBar = false;
  }
  

  

}

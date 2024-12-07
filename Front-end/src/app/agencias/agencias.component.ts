import { Component, AfterViewInit, OnInit } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-agencias',
  templateUrl: './agencias.component.html',
  styleUrl: './agencias.component.css'
})
export class AgenciasComponent implements OnInit {
  map!: L.Map;
  selectedProvincia: string = ''; // Provincia seleccionada
  provincias: string[] = ['Ica', 'Chincha', 'Pisco', 'Palpa', 'Nazca']; // Lista de provincias
  agencies = [
    { name: 'RapiExpress Ica', address: 'Av. Principal 123, Ica', coords: [-14.0681, -75.7254] as L.LatLngTuple, provincia: 'Ica', schedule: { weekdays: '8:00 am - 6:00 pm', saturday: '9:00 am - 1:00 pm', sunday: 'Cerrado' } },
    { name: 'RapiExpress Chincha', address: 'Calle Comercio 456, Chincha', coords: [-13.4095, -76.1323] as L.LatLngTuple, provincia: 'Chincha', schedule: { weekdays: '8:30 am - 5:30 pm', saturday: '9:30 am - 1:30 pm', sunday: 'Cerrado' } },
    { name: 'RapiExpress Pisco', address: 'Plaza Mayor 789, Pisco', coords: [-13.7104, -76.2032] as L.LatLngTuple, provincia: 'Pisco', schedule: { weekdays: '8:00 am - 5:00 pm', saturday: '9:00 am - 12:30 pm', sunday: 'Cerrado' } },
    { name: 'RapiExpress Palpa', address: 'Jr. Central 101, Palpa', coords: [-14.5266, -75.1878] as L.LatLngTuple, provincia: 'Palpa', schedule: { weekdays: '7:00 am - 3:00 pm', saturday: '8:00 am - 12:00 pm', sunday: 'Cerrado' } },
    { name: 'RapiExpress Nazca', address: 'Calle Lima 202, Nazca', coords: [-14.8356, -74.9325] as L.LatLngTuple, provincia: 'Nazca', schedule: { weekdays: '9:00 am - 4:30 pm', saturday: '10:00 am - 2:00 pm', sunday: 'Cerrado' } }
  ];
  filteredAgencies = [...this.agencies]; // Lista filtrada de agencias

  ngOnInit(): void {
    // Inicializar el mapa
    this.map = L.map('map').setView([-14.0681, -75.7254], 7); // Zoom inicial
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 180,
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);

    // Agregar marcadores al mapa
    this.agencies.forEach(agency => {
      const marker = L.marker(agency.coords).addTo(this.map);
      marker.bindPopup(`
        <strong>${agency.name}</strong><br>
        ${agency.address}<br>
        Horario:<br>
        Lunes a viernes: ${agency.schedule.weekdays}<br>
        Sábado: ${agency.schedule.saturday}<br>
        Domingo: ${agency.schedule.sunday}
      `);
    });
  }

  // Filtrar agencias por provincia seleccionada
  resetMap(): void {
    // Ajusta las coordenadas y el nivel de zoom según la vista inicial deseada
    const initialCoords: [number, number] = [-13.524, -71.967]; // Ejemplo: Coordenadas iniciales de Perú
    const initialZoom = 6; // Nivel de zoom inicial
  
    this.map.setView(initialCoords, initialZoom); // Centra el mapa y ajusta el zoom
  }

  // Mover el mapa a la agencia seleccionada
  goToAgency(agency: any): void {
    this.map.setView(agency.coords, 15); // Zoom al marcador
    L.popup()
      .setLatLng(agency.coords)
      .setContent(`
        <strong>${agency.name}</strong><br>
        ${agency.address}<br>
        Horario:<br>
        Lunes a viernes: ${agency.schedule.weekdays}<br>
        Sábado: ${agency.schedule.saturday}<br>
        Domingo: ${agency.schedule.sunday}
      `)
      .openOn(this.map);
  }

  filterAgencies(): void {
    this.filteredAgencies = this.agencies.filter(agency =>
      agency.provincia.toLowerCase().includes(this.selectedProvincia.toLowerCase())
    );
  }
  
  centerMap(coords: L.LatLngTuple): void {
    this.map.setView(coords, 15); // Centrar el mapa con un zoom de 15
  }
}
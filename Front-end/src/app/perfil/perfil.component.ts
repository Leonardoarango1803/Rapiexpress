import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../core/service/user.service';
import { AuthService } from '../core/service/auth.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {
  activeSection: string = 'datosPersonales';
  user: any = {};
  envios: any[] = [];
  showNotification: boolean = false;
  notificationMessage: string = '';
  filteredEnvios: any[] = []; // Envíos filtrados para el buscador
  searchTerm: string = ''; 
  estadoEnvio: any;
  isModalOpen = false;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const userId = this.authService.getUsuarioId();
    if (userId) {
      this.loadUserData(userId);
      this.loadUserEnvios(userId);
    } else {
      console.error('Error: No se pudo obtener el ID del usuario');
    }
  }

  setActiveSection(section: string): void {
    this.activeSection = section;
  }

  // Cargar los datos del usuario desde el backend
  loadUserData(userId: number): void {
    this.userService.getUserById(userId).subscribe(
      (data) => {
        this.user = data;
      },
      (error) => {
        console.error('Error al cargar los datos del usuario:', error);
      }
    );
  }

  // Cargar los envíos del usuario
  loadUserEnvios(userId: number): void {
    this.userService.getUserEnvios(userId).subscribe(
      (data: any) => {
        this.envios = data;
        this.filteredEnvios = [...this.envios]; // Inicialmente todos los envíos
      },
      (error) => {
        console.error('Error al cargar los envíos del usuario:', error);
      }
    );
  }

  // Filtrar envíos por código de rastreo
  filterEnvios(): void {
    this.filteredEnvios = this.envios.filter((envio) =>
      envio.codigoRastreo.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  // Actualizar los datos del usuario
  updateUser(): void {
    this.userService.updateUserById(this.user.usuarioid, this.user).subscribe(
      () => {
        alert('Datos actualizados con éxito');
        console.log('Se guardaron los datos exitosamente!');
      },
      (error) => {
        console.error('Error al actualizar los datos del usuario:', error);
      }
    );
  }

  logout(): void {
    const confirmation = confirm('¿Está seguro de que desea cerrar sesión?');
    if (confirmation) {
      localStorage.removeItem('authToken');
      this.showFloatingMessage('Sesión cerrada', 3000);
      this.router.navigate(['/login']);
      console.log('Sesión cerrada');
    } else {
      console.log('Cierre de sesión cancelado');
    }
  }

  showFloatingMessage(message: string, duration: number): void {
    this.notificationMessage = message;
    this.showNotification = true;
    setTimeout(() => {
      this.showNotification = false;
    }, duration);
  }

  openModal(historialEnvios: any[]): void {
    // Asignar solo el historial del envío específico
    this.estadoEnvio = { historialEnvios };
    this.isModalOpen = true; // Esto muestra el modal
  }
  
  closeModal(): void {
    this.isModalOpen = false; // Esto oculta el modal
  }

}
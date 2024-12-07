import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { InicioComponent } from './inicio/inicio.component';
import { RegistrouserComponent } from './registrouser/registrouser.component';
import { LoginComponent } from './login/login.component';
import { RastreoComponent } from './rastreo/rastreo.component';
import { EnviarComponent } from './enviar/enviar.component';
import { AuthGuard } from './core/guard/auth.guard';
import { AuthenticatedGuard } from './core/guard/authenticated.guard';
import { AgenciasComponent } from './agencias/agencias.component';
import { PanelComponent } from './panel/panel.component';
import { PerfilComponent } from './perfil/perfil.component';

// importar y generar componentes
const routes: Routes = [
  { path: 'inicio', component: InicioComponent,},
  {path:'registrar', component:RegistrouserComponent},
  {path: 'login', component:LoginComponent, },
  {path: 'rastreo', component:RastreoComponent },
  {path: 'enviar', component:EnviarComponent,},
  {path:  'agencias', component:AgenciasComponent},
  {path: 'panel', component:PanelComponent},
  {path: 'perfil', component:PerfilComponent},
  
  // rutas vacias o incorrectas redirijen al inicio
  { path: '', redirectTo: 'inicio', pathMatch: 'full' },
  { path: '**', redirectTo: 'inicio'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { InicioComponent } from './inicio/inicio.component';
import { RegistrouserComponent } from './registrouser/registrouser.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { LoginComponent } from './login/login.component';
import { RastreoComponent } from './rastreo/rastreo.component';
import { EnviarComponent } from './enviar/enviar.component';
import { AgenciasComponent } from './agencias/agencias.component';
import { PanelComponent } from './panel/panel.component';
import { PerfilComponent } from './perfil/perfil.component';


@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    RegistrouserComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RastreoComponent,
    EnviarComponent,
    AgenciasComponent,
    PanelComponent,
    PerfilComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideHttpClient(withFetch())
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

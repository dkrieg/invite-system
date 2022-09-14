import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppMenuModule } from './AppMenuModule';
import { HttpClientModule } from "@angular/common/http";
import { ManagementComponent } from "./management-module/management.component"
import { OrganizationComponent } from "./organization/organization.component"

import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [AppComponent,ManagementComponent,OrganizationComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    AppMenuModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas : [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule {}

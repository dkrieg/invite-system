import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatListModule } from '@angular/material/list';
import { MatRadioModule} from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { HttpClientModule} from '@angular/common/http';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTooltipModule } from '@angular/material/tooltip';
import { NgImageSliderModule } from 'ng-image-slider';
import {MatDialogModule} from '@angular/material/dialog';
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule,NgxMatTimepickerModule } from '@angular-material-components/datetime-picker'

import {BrowserAnimationsModule} from '@angular/platform-browser/animations'
import { NgxLoadingModule } from 'ngx-loading';
import { AppComponent } from './app.component';
import { AngularDualListBoxModule } from 'angular-dual-listbox';
import { SweetAlert2Module} from '@sweetalert2/ngx-sweetalert2';


import { ReserveclubComponent } from './reserveclub/reserveclub.component';


@NgModule({
  declarations: [
    AppComponent,
    ReserveclubComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatTooltipModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatCheckboxModule,
    MatListModule,
    HttpClientModule,
    AngularDualListBoxModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgImageSliderModule,
    NgxMatTimepickerModule,
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,
    SweetAlert2Module,
    MatDialogModule,
    NgxLoadingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

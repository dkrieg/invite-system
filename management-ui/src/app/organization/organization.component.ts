import { Component, NgModule, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SharedService } from '../shared.service';
@Component({
  selector: 'organization',
  templateUrl: './organization.component.html',
  styleUrls: [],
})
export class OrganizationComponent implements OnInit {
    registerForm: any;
    submitted = false;

    constructor(private formBuilder: FormBuilder, public sharedService:SharedService) { }

    ngOnInit() {
        
        this.registerForm = this.formBuilder.group({
            providerGroup: ['', Validators.required],
            name: ['', Validators.required],
            segment: ['', Validators.required],
            address: ['', [Validators.required]],
            city: ['', [Validators.required]],
            state: ['', Validators.required],
            zip: ['', Validators.required]
        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        // display form values on success
        alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.registerForm.value, null, 4));
    }

    onReset() {
        this.submitted = false;
        this.registerForm.reset();
    }
}

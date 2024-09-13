import { Component } from '@angular/core';
import { DefaultloginComponent } from '../../components/defaultlogin/defaultlogin.component';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PrimaryInputComponent } from '../../components/primary-input/primary-input.component';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { ToastrService } from 'ngx-toastr';


interface SignUpForm {
  name: string;
  email: string;
  password: string;
  passwordConfirm: string;
}
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    DefaultloginComponent,
    ReactiveFormsModule,
    PrimaryInputComponent,
  ],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignUpComponent {
  signupForm!: FormGroup;
  sigUpForm: any;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastrService
  ) {
    this.signupForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      passwordConfirm: new FormControl('', [Validators.required, Validators.minLength(6)])
    });
  }

  submit() {
    this.loginService.login(this.signupForm.value.email, this.signupForm.value.password).subscribe({
      next: () => this.toastr.success("Login feito com sucesso"), // Correção aqui
      error: () => this.toastr.error("Erro inesperado! Tente novamente") // Correção aqui
    });
  }

  navigate() {
    this.router.navigate(["/login"]);
  }
}

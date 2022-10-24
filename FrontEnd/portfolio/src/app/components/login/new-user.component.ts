import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { NewUser } from '../../models/new-user';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  public newUserForm;
  name: string;
  userName: string;
  email: string;
  password: string;
  personId: number;
  rols: string[] = ['admin'];

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initForm();
  }

  createNewUser(): void {
    const newUser = new NewUser(this.name = this.newUserForm.get('name').value,
      this.userName = this.newUserForm.get('userName').value,
      this.email = this.newUserForm.get('email').value, 
      this.password = this.newUserForm.get('password').value, 
      this.personId = 1, 
      this.rols);
    this.authService.newUser(newUser).subscribe({
      next: (data) => {
        alert("User has been added!");
        this.router.navigate(['']);
      },
      error: (err) => {
        alert("Error: User has not been added!");
        this.router.navigate(['']);
      }
    })
  }

  initForm() {
    this.newUserForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
      name: ['', Validators.required],
      email: ['', Validators.required]
    })
  }
}
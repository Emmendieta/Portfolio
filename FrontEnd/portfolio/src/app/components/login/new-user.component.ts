import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { NewUser } from '../../models/new-user';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent implements OnInit {

  name: string = "";
  userName: string = "";
  email: string = "";
  password: string = "";
  personId = 1;
  rols: string[] = ['admin'];

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  createNewUser(): void {
    const newUser = new NewUser(this.name, this.userName,this.password, this.email, this.personId, this.rols);
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
}

import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  user: User = { username: '', email: '', password: '' };

  constructor(private userService: UserService, private router: Router, private authService: AuthService) {}

  login(): void {
    this.userService.login(this.user).subscribe(
      response => {
        console.log(response.message);
        this.showModal(response.message);
        this.authService.login(response.role); // Set the user's role
        this.router.navigate(['/user-panel']);
      },
      error => {
        console.error('Login failed:', error);
        // Handle login error
      }
    );
  }

  showModal(message: string): void {
    const modalElement = document.getElementById('loginSuccessModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
      const modalBody = modalElement.querySelector('.modal-body');
      if (modalBody) {
        modalBody.textContent = message;
      }
    }
  }
}
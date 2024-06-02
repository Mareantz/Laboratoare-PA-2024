import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = false;
  private userRole: string | null = null;

  constructor(private router: Router) {}

  login(role: string): void {
    this.isAuthenticated = true;
    this.userRole = role;
  }

  logout(): void {
    this.isAuthenticated = false;
    this.userRole = null;
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }

  getRole(): string | null {
    return this.userRole;
  }
}

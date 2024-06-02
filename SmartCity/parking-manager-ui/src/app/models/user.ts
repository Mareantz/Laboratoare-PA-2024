// src/app/models/user.ts
export interface User {
  userId?: number;
  username: string;
  email: string;
  password: string;
  role?: string;
}

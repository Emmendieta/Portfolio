import { LoginUser } from './login-user';

export class NewUser extends LoginUser {
   
    name: string;
    email: string;
    authorities: string[];
    
}

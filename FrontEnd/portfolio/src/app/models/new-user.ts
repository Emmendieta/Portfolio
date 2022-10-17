import { LoginUser } from './login-user';

export class NewUser extends LoginUser {
    
    name: string;
    email: string;
    personId: number;
    rols: string[];


    constructor(name: string, userName: string, password: string, email: string, personId: number, rols: string[]){
        super(userName, password);
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.personId = personId;
        this.rols = rols;
        
    }
    
}

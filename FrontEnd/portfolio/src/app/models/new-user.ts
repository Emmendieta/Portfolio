export class NewUser {
    
    name!: string;
    userName!: string;
    email!: string;
    password!: string;
    personId: number;
    rols!: string[];


    constructor(name: string, userName: string, email: string, password: string, personId: number, rols: string[]){
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.personId = personId;
        this.rols = rols;       
    }
    
}

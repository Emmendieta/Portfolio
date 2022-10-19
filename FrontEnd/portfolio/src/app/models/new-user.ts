export class NewUser {
    
    name!: string;
    userName!: string;
    password!: string;
    email!: string;
    personId: number;
    authorities!: string[];


    constructor(name: string, userName: string, password: string, email: string, personId: number, authorities: string[]){
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.personId = personId;
        this.authorities = authorities;
        
    }
    
}

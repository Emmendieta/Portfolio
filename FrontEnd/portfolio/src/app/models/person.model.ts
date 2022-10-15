export class Person {
    id?: number;
    name: string;
    lastName: string;
    age: Date;
    title: string;
    about: string;
    province: string;
    country: string;
    image: string;

    constructor(name: string, lastName: string, age: Date, title: string, about: string, province: string, country: string, image: string){
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.title = title;
        this.about = about;
        this.province = province;
        this.country = country;
        this.image = image;
    }

}
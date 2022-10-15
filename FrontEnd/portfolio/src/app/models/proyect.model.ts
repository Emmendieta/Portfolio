export class Proyect {
    id?: number;
    name: string;
    description: string;
    link: string;
    image: string;
    dateStart: Date;
    dateEnd: Date;
    personId: number;

    constructor(name: string, description: string, link: string, image: string, dateStart: Date, dateEnd: Date, personId: number){
        this.name = name;
        this.description = description;
        this.link = link;
        this.image = image;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.personId = 1;
    }
}
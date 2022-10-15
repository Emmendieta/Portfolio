export class Education {
    id?: number;
    name: string;
    title: string;
    description: string;
    dateStart: Date;
    dateEnd: Date;
    image: string;
    personId: number;

    constructor(name: string, title: string, description: string, dateStart: Date, dateEnd: Date, image: string, personId: number){
        this.name = name;
        this.title = title;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.image = image;
        this.personId = 1;
    }
}
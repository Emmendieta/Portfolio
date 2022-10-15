export class HardSoft {
    id?: number;
    name: string;
    percent: number;
    image: string;
    personId: number;

    constructor(name: string, percent: number, image: string, personId:number) {
        this.name = name;
        this.percent = percent;
        this.image = image;
        this.personId = 1;
    }
}
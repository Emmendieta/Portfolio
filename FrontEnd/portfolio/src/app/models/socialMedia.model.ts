export class SocialMedia{
    id?: number;
    name: string;
    image: string;
    url: string;
    personId: number;

    constructor(name: string, image: string, url: string, personId: number){
        this.name = name;
        this.image = image;
        this.url = url;
        this.personId = 1;
    }
}
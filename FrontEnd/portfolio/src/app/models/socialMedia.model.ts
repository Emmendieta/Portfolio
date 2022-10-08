export class socialMedia{
    id?: number;
    nameSM: string;
    imageSM: string;
    urlSM: string;
    personId: number;

    constructor(nameSM: string, imageSM: string, urlSM: string, personId: number){
        this.nameSM = nameSM;
        this.imageSM = imageSM;
        this.urlSM = urlSM;
        this.personId = personId;
    }
}
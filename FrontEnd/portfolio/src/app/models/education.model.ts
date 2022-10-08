export class education {
    id?: number;
    nameEd: string;
    titleEd: string;
    descriptionEd: string;
    dateStartEd: Date;
    dateEndEd: Date;
    imageEd: String;
    personId: number;

    constructor(nameEd: string, titleEd: string, descriptionEd: string, dateStarteEd: Date, dateEndEd: Date, 
        imageEd: string, personId: number){
            this.nameEd = nameEd;
            this.titleEd = titleEd;
            this.descriptionEd = descriptionEd;
            this.dateStartEd = dateStarteEd;
            this.dateEndEd = dateEndEd;
            this.imageEd = imageEd;
            this.personId = personId;
    }
}
export class VideoToSend {
  constructor( public email: string, public url: string,
               public title: string, public description: string,
               public display: number, public photoUrl: string ) {}
}

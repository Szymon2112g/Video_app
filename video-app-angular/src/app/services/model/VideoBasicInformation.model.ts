export class VideoBasicInformation {
  constructor(public id: number, public url: string,
              public title: string, public description: string,
              public firstName: string, public lastName: string,
              public userId: number, public display: number, public photoUrl: string,
              public date: string, public likes: number, public dislikes: number) { }
}

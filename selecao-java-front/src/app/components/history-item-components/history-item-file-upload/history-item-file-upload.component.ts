import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FileUploader, FileUploadModule} from 'ng2-file-upload';

@Component({
  selector: 'app-history-item-file-upload',
  templateUrl: './history-item-file-upload.component.html',
  styleUrls: ['./history-item-file-upload.component.scss']
})
export class HistoryItemFileUploadComponent implements OnInit {

  @ViewChild('fileInput') fileInput: ElementRef;

  uploader: FileUploader;
  isDropOver: boolean;
  hiddenProgressBar: boolean;

  ngOnInit(): void {
    this.hiddenProgressBar = true;
    const headers = [{name: 'Accept', value: 'application/json'}, {name: 'Access-Control-Allow-Methods', value: 'GET, POST, DELETE, PUT'}];
    this.uploader = new FileUploader({
      method: 'post',
      url: 'http://localhost:8080/pricehistory/upload/file/csv',
      autoUpload: true,
      headers,
      additionalParameter: { hasHeader: 1 }});

    this.uploader.onCompleteAll = () => this.finish();
    this.uploader.onBeforeUploadItem = () => this.showProgressBar();
  }

  finish(){
    this.hiddenProgressBar = true;
    alert('File uploaded');
  }

  showProgressBar(){
    this.hiddenProgressBar = false;
  }

  fileOverAnother(e: any): void {
    this.isDropOver = e;
  }

  fileClicked() {
    this.fileInput.nativeElement.click();
  }

}

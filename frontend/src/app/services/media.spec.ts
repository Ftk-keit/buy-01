import {TestBed} from '@angular/core/testing';

import {MediaService} from './media.service';
import {provideHttpClient} from '@angular/common/http';

describe('Media', () => {
    let service: MediaService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            imports:[

            ],
            providers:[
                provideHttpClient()
            ]
        });
        service = TestBed.inject(MediaService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});

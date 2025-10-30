import {TestBed} from '@angular/core/testing';

import {UtilsService} from './utils.service';
import {provideHttpClient} from '@angular/common/http';

describe('Utils', () => {
    let service: UtilsService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers:[
                provideHttpClient()
            ]
        });
        service = TestBed.inject(UtilsService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});

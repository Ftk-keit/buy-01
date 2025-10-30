import {TestBed} from '@angular/core/testing';

import {AuthStateService} from './auth.state.service';
import {provideHttpClient} from '@angular/common/http';

describe('AuthStateService', () => {
    let service: AuthStateService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers:[
                provideHttpClient()
            ]
        });
        service = TestBed.inject(AuthStateService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});

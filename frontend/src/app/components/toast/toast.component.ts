import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subject, takeUntil} from 'rxjs';
import {ToastService} from '../../services/toast.service';
import {Toast} from '../../entity/Toast';
import {trigger, transition, style, animate} from '@angular/animations';

@Component({
    selector: 'app-toast',
    imports: [],
    templateUrl: './toast.component.html',
    styleUrl: './toast.component.scss',
    animations: [
        trigger('slideIn', [
            transition(':enter', [
                style({ transform: 'translateX(100%)', opacity: 0 }),
                animate('300ms ease-out', style({ transform: 'translateX(0)', opacity: 1 }))
            ]),
            transition(':leave', [
                animate('300ms ease-in', style({ transform: 'translateX(100%)', opacity: 0 }))
            ])
        ])
    ]
})
export class ToastComponent implements OnInit, OnDestroy {

    toasts: Toast[] = [];
    private destroy$ = new Subject<void>();

    constructor(
        private toastService: ToastService,
    ) {
    }

    ngOnInit() {
        this.toastService.toasts$
            .pipe(takeUntil(this.destroy$))
            .subscribe((toasts: any) => {
                this.toasts = toasts;
            })
    }

    ngOnDestroy() {
        this.destroy$.next();
        this.destroy$.complete();
    }

    getIcon(type: Toast['type']): string {
        switch (type) {
            case 'success':
                return '✔️';
            case 'error':
                return '❌';
            case 'info':
                return 'ℹ️';
            case 'warning':
                return '⚠️';
            default:
                return 'ℹ️';
        }
    }

    trackByFn(_: number, toast: Toast) {
        return toast.id
    }

    removeToast(id: string) {
        this.toastService.remove(id)
    }
}

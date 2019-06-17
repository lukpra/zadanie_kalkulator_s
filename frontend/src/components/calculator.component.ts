import {Component, Vue} from 'vue-property-decorator';
import {RestAPI} from '@/service/rest-api';

@Component
export default class CalculatorComponent extends Vue {
    public options = [
        { value: 'pl', text: 'Poland' },
        { value: 'gb', text: 'United Kingdom' },
        { value: 'de', text: 'Germany' },
    ];

    public selectedCountry: string = 'pl';
    public dailyWage: number = 1;
    public loadingData: boolean = false;
    public data: { netto: number, country: string, dailyWage: number} | null = null;

    public get formValid() {
        return !Number.isNaN(this.dailyWage) && this.dailyWage > 0 && !this.loadingData ? true : false;
    }

    public handleClick() {
        this.loadingData = true;
        const serverResponse = RestAPI.getNetto(this.dailyWage, this.selectedCountry);

        if (serverResponse) {
            serverResponse
                .then((response) => this.data = response.data)
                .catch(() => this.data = null)
                .then(() => this.loadingData = false);
        }
    }

    public get onDailyWageInvalid() {
        return this.formValid ? '' : 'Wartość musi być większa od 0';
    }
}

import axios from 'axios';

export class RestAPI {
    public static URL: string = 'http://localhost:8080/salary/calculate';

    public static async getNetto(dailyWage: number, countryCode: string) {
        return await axios.get(RestAPI.URL, {
            params: {
                dailyWage,
                countryCode,
            },
        });
    }
}


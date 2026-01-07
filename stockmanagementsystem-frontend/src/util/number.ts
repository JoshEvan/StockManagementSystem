const DZ = 12;

export function pcsToDzs(pcs: number): {dzs: number, pcs: number} {
    const dzs = Math.floor(pcs / DZ);
    const remainingPcs = pcs % DZ;
    return { dzs, pcs: remainingPcs };
}


export function pcsToDzsFmt(pcs: number): string {
    const dzs = Math.floor(pcs / DZ);
    const remainingPcs = pcs % DZ;
    return `${dzs} dzs${remainingPcs ? ` + ${remainingPcs} pcs` : ''}`;
}

export function randomDigit(digit: number): string {
    // Generates a number between 0 (inclusive) and 9000 (exclusive)
    const randomNum = Math.random() * 9 * (10 ** (digit - 1)); 
    // Adds 1000 to ensure the result is always a 4-digit number
    const fourDigitNum = Math.floor(1 * (10 ** (digit - 1)) + randomNum); 
    return fourDigitNum.toString();
}

export function todayfmt(): string {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const day = String(today.getDate()).padStart(2, '0');
    return `${year}${month}${day}`;
}
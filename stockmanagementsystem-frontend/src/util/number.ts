const DZ = 12;

function pcsToDzs(pcs: number): {dzs: number, pcs: number} {
    const dzs = Math.floor(pcs / DZ);
    const remainingPcs = pcs % DZ;
    return { dzs, pcs: remainingPcs };
}


export function pcsToDzsFmt(pcs: number): string {
    const dzs = Math.floor(pcs / DZ);
    const remainingPcs = pcs % DZ;
    return `${dzs} dzs${remainingPcs ? ` + ${remainingPcs} pcs` : ''}`;
}
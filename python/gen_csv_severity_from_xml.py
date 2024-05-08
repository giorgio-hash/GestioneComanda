import xml.etree.ElementTree as Et
import csv
from collections import defaultdict
from pathlib import Path


def parse_checkstyle_report(xml_file, severity, skip=False):
    tree = Et.parse(xml_file)
    root = tree.getroot()

    error_counts = defaultdict(int)
    for file in root.findall('file'):
        for error in file.findall('error'):
            if error.get('severity') == severity or skip is True:
                source = error.get('source')
                # Remove the prefix
                error_type = source.split('.')[-1]
                error_counts[error_type] += 1
    return error_counts


def check_and_create_folder(folder):
    path_folder = Path(folder)
    if not path_folder.exists():
        # create the folder if not exists
        path_folder.mkdir(parents=True, exist_ok=True)


def write_error_counts_to_csv(error_counts, file, severity):
    target_folder = "target"
    output_folder = "output"
    csv_folder = "csv"
    check_and_create_folder(Path(target_folder) / output_folder)
    check_and_create_folder(Path(target_folder) / output_folder / csv_folder)
    output_file = Path(target_folder) /output_folder / csv_folder / file
    with open(output_file, mode='w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow([severity, 'Count'])
        for error, count in error_counts.items():
            writer.writerow([error, count])
    print(f"CSV file '{output_file}' has been created successfully.")

import csv
import matplotlib.pyplot as plt
from pathlib import Path


def read_error_counts_from_csv(csv_file):
    error_counts = {}
    csv_file = Path("target/output/csv") / csv_file
    with open(csv_file, mode='r', encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile)
        header_row = next(reader)
        title = header_row[0]
        for row in reader:
            error_counts[row[0]] = int(row[1])
    return title, error_counts


def check_and_create_folder(folder):
    path_folder = Path(folder)
    if not path_folder.exists():
        # create the folder if not exists
        path_folder.mkdir(parents=True, exist_ok=True)


def plot_pie_chart(error_counts, output_image, title):

    target_folder = "target"
    output_folder = "output"
    image_folder = "images"
    check_and_create_folder(Path(target_folder) / output_folder)
    check_and_create_folder(Path(target_folder) / output_folder / image_folder)
    output_file = Path(target_folder) /output_folder / image_folder / output_image

    errors = list(error_counts.keys())
    counts = list(error_counts.values())

    total_count = sum(counts)
    percentages = [count / total_count * 100 for count in counts]

    threshold_percentage = 1  # Set the threshold to <= 1%
    small_indices = [i for i, perc in enumerate(percentages) if perc < threshold_percentage]

    other_count = sum([counts[i] for i in small_indices])
    other_percentage = sum([percentages[i] for i in small_indices])

    # Groups lower percentages under other
    if small_indices:
        errors = [error for i, error in enumerate(errors) if i not in small_indices]
        errors.append("Other")
        counts = [count for i, count in enumerate(counts) if i not in small_indices]
        counts.append(other_count)
        percentages = [perc for i, perc in enumerate(percentages) if i not in small_indices]
        percentages.append(other_percentage)

    # Generate the pie chart
    plt.figure(figsize=(10, 7))
    patches, texts, _ = plt.pie(counts, labels=errors, autopct='%1.1f%%', startangle=140, pctdistance=0.85)
    plt.axis('equal')
    plt.title(title + " Distribution")

    plt.savefig(output_file, bbox_inches='tight')
    plt.close()
    print(f"Image saved as '{output_file}' successfully.")

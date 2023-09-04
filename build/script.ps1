$dockerhub_repo = $args[0]

Set-Location -Path "../source"

# Use Split-Path to get the folder names properly
$folder_names = Get-ChildItem -Directory | ForEach-Object { $_.Name }

foreach ($folder_name in $folder_names) {
    Write-Host "Processing folder: $folder_name"
    Get-Location

    Set-Location -Path ".\$folder_name"
    Set-ItemProperty -Path .\docker-entrypoint.sh -Name IsReadOnly -Value $false
    Unblock-File -Path .\docker-entrypoint.sh

    # maven build
    docker run -it --rm --name my-maven-project -v "$env:USERPROFILE\.m2:/root/.m2" -v ${PWD}:/app maven:3.8.3-amazoncorretto-17 mvn clean install -f app/pom.xml '-Dmaven.test.skip=true'

    # docker build
    docker build . -t "$dockerhub_repo/$folder_name"
    docker push "$dockerhub_repo/$folder_name"

    # back to source path
    Set-Location -Path ".."
}

Set-Location -Path "../build"

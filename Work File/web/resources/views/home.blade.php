@extends('app')

@section('content')
<div class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">Home</div>

				<div class="panel-body">
					You are logged in!
				</div>
			</div>
		</div>

		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">Errors</div>

				<div class="panel-body">
					@foreach ($err as $index => $errors) 
					<ul>
						@if ($errors->archive == 0)
						<li><input type="checkbox" name="{{$errors->id}}" value=" "> {{$errors->id}} {{$errors->Name}} {{$errors->Message}} {{$errors->assetcode}}</li>
						@endif
					</ul>
					@endforeach
				</div>
			</div>
		</div>
	</div>
</div>
@endsection